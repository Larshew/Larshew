#include <iostream>
#include <stdlib.h>
#include <WinSock2.h>

#define SERVER_DNS "openapi.airkorea.or.kr"
//#define SERVER_DNS "openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=Á¾·Î±¸&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D&ver=1.3"
#define SERVICE_KEY "V6i2Rt1FL4jsYItn2EEcQm6Aw6AB4C0WsWt9YbSXem%2FiCQjiMGQnfVzrGUtkbKZY0dA7EJawhznH%2FEb6wtHowg%3D%3D"
#define PORT_NUM 4000
#define BUFF_SIZE 10000

using namespace std;

char recvMessage[10001];

void Error_Handling(const char* message) {
	cerr << message << endl;
	exit(1);
}

int main()
{
	WSADATA wsaData;
	hostent* host;
	SOCKET clntsock;
	SOCKADDR_IN clnt_addr;

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
		Error_Handling("Window Socket Startup Error");
	}

	host = gethostbyname(SERVER_DNS);

	int i;
	for (i = 0; host->h_aliases[i]; i++) 
		cout << "Aliases " << i + 1 << " : " << host->h_aliases[i] << endl;

	cout << "Address Type : " << ((host->h_addrtype == AF_INET) ? "AF_INET" : "AF_INET6") << endl;
	
	for (i = 0; host->h_addr_list[i]; i++) {
		printf("IP addr %d : %s\n", i + 1, inet_ntoa(*(in_addr*)host->h_addr_list[i]));
	}
	
	clntsock = socket(host->h_addrtype, SOCK_DGRAM, IPPROTO_UDP);

	memset(&clnt_addr, 0, sizeof(clnt_addr));
	clnt_addr.sin_family = host->h_addrtype;
	clnt_addr.sin_addr.s_addr = inet_addr(host->h_addr_list[0]);
	clnt_addr.sin_port = htons((short)PORT_NUM);
	
	if (connect(clntsock, (SOCKADDR*)&clnt_addr, sizeof(clnt_addr))){
		Error_Handling("Connect Error!");
	}

/*	if (bind(clntsock, &clnt_addr, sizeof(SOCKADDR_IN)) == SOCKET_ERROR) {
		Error_Handling("Socket Binding Error");
	}
*/
	cout << "Connected Complete!" << endl;
	const char* sendM = "GET";
	send(clntsock, sendM, strlen(sendM), 0);
	cout << "Send Complete!" << endl;

	if (recv(clntsock, recvMessage, BUFF_SIZE, 0) == -1) {
		Error_Handling("Receiving Message Error!");
	}
	cout << recvMessage;
	WSACleanup();
	return 0;
}