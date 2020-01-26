import sys, os
sys.path.append(os.pardir)
import numpy as np
from dataset.mnist import load_mnist

def cross_enthropy_error(y,t):
    theta = 1e-7
    batch_size = y.shape[0]
    return -np.sum(np.log(y+theta)) / batch_size


(x_train, t_train), (x_test, t_test) = load_mnist(normalize=True, one_hot_label = True)

x_batch = x_train[np.random.choice(60000,100)]

