# -*- coding: utf-8 -*-
"""
Created on Thu Jan 23 15:12:32 2020

@author: ravor
"""

import sys, os
import pickle
sys.path.append(os.pardir)
import numpy as np
from dataset.mnist import load_mnist
from PIL import Image

def sigmoid(x):
    return 1/(1+np.exp(x))
    
def get_data():
    (x_train, t_train), (x_test,y_test) = \
        load_mnist(normalize=True, flatten=True, one_hot_label = False)
    return x_test, y_test

def init_network():
    with open("sample_weight.pkl", 'rb') as f:
        network = pickle.load(f)
    
    return network

def softmax(array):
    c = np.max(array)
    array = array - c
    
    exp_arr = np.exp(array)
    sum_exparr = np.sum(exp_arr)
    return exp_arr / sum_exparr

def predict(network,x):
    W1, W2, W3 = network['W1'], network['W2'], network['W3']
    b1, b2, b3 = network['b1'], network['b2'], network['b3']
    
    a1 = np.dot(x,W1) + b1
    z1 = sigmoid(a1)
    a2 = np.dot(z1,W2) + b2
    z2 = sigmoid(a2)
    a3 = np.dot(z2,W3) + b3   
    y = softmax(a3)
    
    return y


x,t = get_data()
network = init_network()

accuracy_cnt = 0
for i in range(len(x)):
    y = predict(network,x[i])
    p = np.argmax(y)
    if p == t[i]:
        accuracy_cnt += 1
    print(str(i) + ", " + str(accuracy_cnt) + '\n')

print("Accuracy  : " + str(float(accuracy_cnt) / len(x)))
