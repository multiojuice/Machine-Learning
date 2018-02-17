import numpy as np



def nonlin(x,deriv=False):
    if(deriv == True):
        return x*(1-x)

    return 1/(1+np.exp(-x))


# the data to look through

DATA = np.array([[0,0,1],
                [0,1,1],
                [1,0,1],
                [1,1,1]])

ANSWERS = np.array([[0],
                    [1],
                    [1],
                    [0]])


np.random.seed(1)

syn0 = 2*np.random.random((3,4)) -1
syn1 = 2*np.random.random((4,1)) -1

for iterration in range(0,50000):
    
    #Make the layers
    layer1 = DATA
    layer2 = np.dot(layer1,syn0)
    layer3 = np.dot(layer2,syn1)

    #Find Error
    l3_error = layer3 - ANSWERS
    if iterration % 5000 == 0:
        print("Error:" + str(np.mean(np.abs(l3_error))))

    # in what direction is the target value?
    l3_delta = l3_error*nonlin(layer3,deriv=True)
    
    # how much did each l1 value contribute to the l2 error 
    l2_error = l3_delta.dot(syn1.T)

    l2_delta = l2_error*nonlin(layer2,deriv=True)

    syn1 += layer2.T.dot(l3_delta)
    syn0 += layer1.T.dot(l2_delta)
