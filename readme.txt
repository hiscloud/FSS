CS471 Project 5
Author:Junyu Lu	41176974


This project is to check how NEH heuristic works on the Flow Shop Scheduling problems. There are 120 work-time matrixes for the input, and each matrix will be processed with NEH in three situations: Flow Shop Scheduling(FSS), Flow Shop Scheduling with Blocking(FSSB), Flow Shop Scheduling with No Wait(FSSNW). Each situation with each function has a Cmax(makespan/total flow time), and a sequence of the ordering. Those can be found in the result table. The results for FSS is also compared to the optimal value found ever(http://mistic.heig-vd.ch/taillard/problemes.dir/ordonnancement.dir/flowshop.dir/best_lb_up.txt).

The project also allow the user to reorder matrixs and get results from FSS, FSSB, or FSSNW.To do that, create/open a text file "input.txt". The first line of "input.txt" should be the number of Tailard Sets(120 this case). Then, to modify a single matrix, type:
Matrix Index(1-120)
FSS(1)/FSSB(2)/FSSNW(3)
the order(ex.3 17 15 8 9 6 5 14 16 7 11 13 18 19 1 4 2 10 20 12)

DataFile containing all the Taillard Sets and the input.txt should be inside the same directory with the source files. 

The result of modified result will be appear in the standard output.

For the Taillard Sets part, the output is stored in the flie "output.csv".Each size of the matrix is plotted 3 gantt chart for FSS, FSSB and FSSNW. Besides in the report, the gantt charts are also in the result file in case the ones for the large matrixes are not clear.

The time it takes to process is about 5 minutes. The rawdata is already stored in  separated csv files. One containing the result, one containing the result and working time matrix, one containing the result and the Cmax matrix. The presented code will produce Cmax matrix with the result.In the same directory, two python files are used to generate tables and gantt charts.

All the sequences are in the table.xls in result directory. Some of the small sequence are copied to the report to compare.



The conclusion of the experiment analysis is that the NEH hueristic is effiencient when working with Flow Shop Schedule Problems. FSS has less blank time compared with other two. FSSNW takes most time.


Info of the machine running the code:
Processor: Intel(R) Core(TM) i5-7300HQ CPU @ 2.50GHz (4 CPUs), ~2.5GHz
Memory: 8192MB RAM