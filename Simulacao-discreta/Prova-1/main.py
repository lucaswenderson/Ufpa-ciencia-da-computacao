import statistics as st
import math 
import dados
import plot
import numpy as np


data = dados.dados
outilierExt = []

amplitudeInterquartil = st.quantiles(data)[2] - st.quantiles(data)[0]

outlierModMin = st.quantiles(data)[0] - 1.5 * amplitudeInterquartil
outlierModMax = st.quantiles(data)[2] + 1.5 * amplitudeInterquartil
outlierExtMin = st.quantiles(data)[0] - 3 * amplitudeInterquartil
outlierExtMax = st.quantiles(data)[2] + 3 * amplitudeInterquartil

for i in data:
    if (i < outlierModMin) or (i > outlierModMax):
        print("Outlier Moderado:", i)
print()
for i in data:
    if (i < outlierExtMin) or (i > outlierExtMax):
        print("Outlier Extremo:", i)
        outilierExt.append(i)

for i in outilierExt:
    data.remove(i)


print("\nMedia:", st.mean(data))
print("Desvio Padrão:", st.stdev(data))


nClasses = round(1 + (3.3 * math.log10(len(data))))
tamanhoClasse = (max(data) - min(data)) / nClasses

print("\nMaximo:",max(data),"Minimo:",min(data))
print("Numero de classes: %.4f" %nClasses)
print("Tamanho das classes: %.4f" %tamanhoClasse)

freqClasses = [0 for i in range(0, 12)]

for i in data:
  if(i <= 22.0904):
    freqClasses[0] += 1
  elif(22.0904 < i <= 23.1522):
    freqClasses[1] += 1
  elif(23.1522 < i <= 24.214):
    freqClasses[2] += 1
  elif(24.214 < i <= 25.2758):
    freqClasses[3] += 1  
  elif(25.2758 < i <= 26.3376):
    freqClasses[4] += 1
  elif(26.3376 < i <= 27.3994):
    freqClasses[5] += 1  
  elif(27.3994 < i <= 28.4612):
    freqClasses[6] += 1
  elif(28.4612 < i <= 29.523):
    freqClasses[7] += 1
  elif(29.523 < i <= 30.5848):
    freqClasses[8] += 1
  elif(30.5848 < i <= 31.6466):
    freqClasses[9] += 1
  elif(31.6466 < i <= 32.7084):
    freqClasses[10] += 1    
  elif(32.7084 < i <= 33.7703):
    freqClasses[11] += 1

print("\nFrequencia das classes:", freqClasses)
#plot.plotHist(data)




