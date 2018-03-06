"""
Owen Sullivan
iris.py
A program to make sense of and predict data for 
    Iris Flowers
"""

# Imports
import sys
import numpy
import scipy
# Matplotlib Utils
import matplotlib
import matplotlib.pyplot as plt
# Pandas Utils
import pandas
from pandas.plotting import scatter_matrix

# sklearn Utils
import sklearn
from sklearn import model_selection
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.naive_bayes import GaussianNB
from sklearn.svm import SVC

# Grab our data
dataURL = "https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data"

# These are the keys to our values in the data set
data_names = ['sepal-length', 'sepal-width', 'petal-length', 'petal-width', 'class']

#create our dataset object to work with
dataset = pandas.read_csv(dataURL , names = data_names)


# Print out some univariate plots to understand each attribute
dataset.hist()
plt.show()
dataset.plot(kind="box",subplots=True,layout=(2,2), sharex=False, sharey=False)
plt.show()

# Print out some Multivariate plots to see data relation
scatter_matrix(dataset)
plt.show()


# Get the data to train, then get the data to predict
array = dataset.values
X = array[:,0:4]
Y = array[:,4]
validation_size = .20
seed = 7
X_train, X_validation, Y_train, Y_validation = model_selection.train_test_split(X, Y, test_size=validation_size, random_state=seed)

scoring = "accuracy"


# Test several different algorithms to see
# What will work best for our problem
models = []
models.append(('LR', LogisticRegression()))
models.append(('LDA', LinearDiscriminantAnalysis()))
models.append(('KNN', KNeighborsClassifier()))
models.append(('CART', DecisionTreeClassifier()))
models.append(('NB', GaussianNB()))
models.append(('SVM', SVC()))


# Evaluate every type of model we set up
results = []
names = []


for name, model in models:
    kfold = model_selection.KFold(n_splits=10, random_state=seed)
    cv_results = model_selection.cross_val_score(model,X_train,Y_train,cv=kfold, scoring=scoring)
    results.append(cv_results)
    names.append(name)
    msg = "%s: %f (%f)" % (name, cv_results.mean(), cv_results.std())
    print(msg)
    
# Viewing these scores, we see that KNN
# Has the best accuracy, so we will go with this :)

fig = plt.figure()
fig.suptitle('Algorithm Comparison')
ax = fig.add_subplot(111)
plt.boxplot(results)
ax.set_xticklabels(names)
plt.show()


# Choose the knn algorithm and check its scores in the predictions
knn = KNeighborsClassifier()
knn.fit(X_train,Y_train)
predictions = knn.predict(X_validation)
print(accuracy_score(Y_validation,predictions))
print(confusion_matrix(Y_validation,predictions))
print(classification_report(Y_validation,predictions))













