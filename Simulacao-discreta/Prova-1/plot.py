import matplotlib.pyplot as plt
import math

def plotHist(data):
  nClasses = round(1 + (3.3 * math.log10(len(data))))
  fig = plt.figure(figsize=(10, 8))
  plt.hist(data, bins = nClasses)
  fig.savefig('histograma.png')

def plot(data, color, name):
  fig = plt.figure(figsize=(10, 8))
  plt.plot(data, color)
  fig.savefig(name)

def doublePlot(data1, data2):
  fig = plt.figure(figsize=(10, 8))
  plt.plot(data1, 'green')
  plt.plot(data2, 'red')
  fig.savefig('observado x teorico.png')