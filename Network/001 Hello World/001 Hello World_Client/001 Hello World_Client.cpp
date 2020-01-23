#include <stdio.h>
#include <stdlib.h>
#include <WinSock2.h>

void Handle_ErrorMessage(const char* message) {
	fputs(message, stderr);
	fputc('\n',stderr);
	exit(1);
}

int main(int argc, char* argv[])
{
	WSADATA wsaData;
	char message[30];
	int strLen;

	SOCKET hSocket;
	SOCKADDR_IN servAddr;

	if (argc != 3) {
		printf("usage : %s <IP> <Port>\n", argv[0]);
		exit(1);
	}

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
		Handle_ErrorMessage("WSAStartup Error");
	}
	
	hSocket = socket(PF_INET, SOCK_STREAM, 0);
	if (hSocket == INVALID_SOCKET) {
		Handle_ErrorMessage("socket() Error");
	}

	memset(&servAddr, 0, sizeof(servAddr));
	servAddr.sin_family = AF_INET;
	servAddr.sin_addr.s_addr = inet_addr(argv[1]);
	servAddr.sin_port = htons(atoi(argv[2]));

	if (connect(hSocket, (SOCKADDR*)&servAddr, sizeof(servAddr) == SOCKET_ERROR)) {
		Handle_ErrorMessage("connect() error!");
	}

	strLen = recv(hSocket,message,sizeof(message)-1,0);

	if (strLen == -1) {
		Handle_ErrorMessage("read() error!");
	}
	printf("MEssage from server : %s\n", message);
	closesocket(hSocket);
	WSACleanup();
	return 0;
}