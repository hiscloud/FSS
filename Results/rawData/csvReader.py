import statistics
import csv
import xlwt
with open("outputnoMX.csv") as f:
    reader= csv.reader(f)
    result=list(reader)

    f = xlwt.Workbook()
    sheet1 = f.add_sheet('DE&GA',cell_overwrite_ok=True)
    
    print("start")
   # print(results[0])
    for i in range(0,360):
        #data=[]
        sheet1.write(i+1,0,result[2*i+1][2])#1-120
        sheet1.write(i+1,1,result[2*i+1][3])#1-3
        sheet1.write(i+1,2,result[2*i][0])#cmax
        sheet1.write(i+1,3,result[2*i+1][0])#time
        sheet1.write(i+1,4,result[2*i+1][1])#calls
        s=""
        for j in range(1,len(result[2*i])):
            s+=result[2*i][j]+" "
            sheet1.write(i+1,5,s)#calls
                       
                   
    print("end")
f.save('rawTable.xls')
