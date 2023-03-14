import statistics as st

data = [
    56, 61, 57, 77, 62, 75, 63, 55, 64, 60, 60, 57, 61, 57, 67, 62, 69, 67, 68,
    59, 80, 41, 85, 12, 62, 68, 75, 89, 99, 110, 65, 72, 65, 61, 68, 73, 65,
    62, 75, 80, 66, 61, 69, 76, 72, 57, 75, 68, 83, 64, 45, 18, 61, 73, 79, 60,
    115, 68, 42, 55, 69, 64, 66, 74, 65, 76, 65, 58, 65, 64, 65, 60, 65, 80,
    66, 80, 68, 55, 66, 71
]
outilierExt = []

print("Moda:", st.mode(data))
print("Media:", st.mean(data))
print("Mediana:", st.median(data))

print()

print("Quartis:", st.quantiles(data))

print()

print("Amplitude:", max(data) - min(data))
print("Variancia:", st.variance(data))
print("Desvio Padrão:", st.stdev(data))
print("Coeficiente De Variação:", (st.stdev(data) / st.variance(data)) * 100)

print()
"""
4)Os outliers são dados que se diferenciam drasticamente de todos os outros. Em outras palavras, um outlier é um valor que foge da normalidade e que pode (e provavelmente irá) causar anomalias nos resultados obtidos por meio de algoritmos e sistemas de análise. Sim, existem outliers no conjunto de dados ofertado como é demonstrado posteriormente
"""

amplitudeInterquartil = st.quantiles(data)[2] - st.quantiles(data)[0]
print("Amplitude Interquartil:", amplitudeInterquartil)

outlierModMin = st.quantiles(data)[0] - 1.5 * amplitudeInterquartil
outlierModMax = st.quantiles(data)[2] + 1.5 * amplitudeInterquartil
outlierExtMin = st.quantiles(data)[0] - 3 * amplitudeInterquartil
outlierExtMax = st.quantiles(data)[2] + 3 * amplitudeInterquartil

print("Outlier Moderado Minimo:", outlierModMin)
print("Outlier Moderado Maximo:", outlierModMax)
print("Outlier Extremo Minimo:", outlierExtMin)
print("Outlier Extremo Maximo:", outlierExtMax)
print()
for i in data:
    if (i < outlierModMin) or (i > outlierModMax):
        print("Outlier Moderado:", i)
print()
for i in data:
    if (i < outlierExtMin) or (i > outlierExtMax):
        print("Outlier Extremo:", i)
        outilierExt.append(i)
"""
5) Entre as razões para o surgimento de outliers temos:
• Erro na coleta de dados – falha em sensor que realiza coleta, erro na atualização da tabela de dados, e outras. 
• Eventos raros – um outlier difícil de se lidar pois situações atípicas podem ocorrer durante as medições.
"""

for i in outilierExt:
    data.remove(i)

print("\n---Sem outliers extremos---\n")

print("Moda:", st.mode(data))
print("Media:", st.mean(data))
print("Mediana:", st.median(data))

print()

print("Quartis:", st.quantiles(data))

print()

print("Amplitude:", max(data) - min(data))
print("Variancia:", st.variance(data))
print("Desvio Padrão:", st.stdev(data))
print("Coeficiente De Variação:", (st.stdev(data) / st.variance(data)) * 100)
