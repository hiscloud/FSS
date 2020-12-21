
import matplotlib.pyplot as plt
import csv


def gatt(t,start):
        """gantt chart
        t working time matrix
        start starting time matrix
        
        
        """
        t.reverse();
        start.reverse();
        for j in range(len(t[0])):  
                for i in range(len(t)):
                        k =  len(t[0])- j # machine number   (54321)           
                        plt.barh(k, t[i][j],left=start[i][j])
                
                        plt.text(start[i][j],k,len(t)-i,color="black",size=1)
        plt.yticks(range(1,len(t[0])+1), range( len(t[0]) ,0,-1))

with open("outputMXp.csv") as f:
        reader=csv.reader(f)
        p=list(reader)
with open("outputMXCmax.csv") as f:
        reader=csv.reader(f)
        c=list(reader)

        
if __name__ == "__main__":
        row=200
        col=20
        l=36494#line for the first lane of the matrix(excel-1)
        t = [[0 for i in range(col)] for i in range(row)]
        start=[[0 for i in range(col)] for i in range(row)]
        for i in range (0,row):
                for j in range(0,col):
                        t[i][j]=int(p[l+i][j])
                        start[i][j]=int(c[l+i][j])-int(p[l+i][j])
                        
        
       
        
        gatt(t,start)
        plt.savefig("test.png", dpi=800)
        plt.show()

