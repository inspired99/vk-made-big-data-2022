## Дз № 1 

# Файлы `mapper.py` и `reducer.py` в соответствующих папках:

* `mapReduceMean` - для расчета среднего
* `mapReduceVar` - для расчета дисперсии

# Файл `Results.txt` - сравнение стандартных методов и mapReduce
 
Запуск mapReduce запускался с помощью Hadoop Streaming командой: 

`hadoop jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar -file /mapReduceVar/mapper.py -mapper "python3 mapper.py" -file /mapReduceVar/reducer.py -reducer "python3 reducer.py" -input /mapReduceVar/AB_NYC_2019.csv -output /outputmeanresult
`
