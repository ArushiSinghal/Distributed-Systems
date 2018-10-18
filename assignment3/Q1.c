#include<stdio.h>
#include<mpi.h>
#include <stdlib.h>
#include <math.h>
#include<string.h>
#define FIRST 0

int main(int argc, char** argv) {
	MPI_Init(NULL, NULL);
	
	int world_size;
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);
	
	int world_rank;
	MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
	
	/******************** task with rank 0 is master ********************/
	if (world_rank == FIRST) {
		int num;
		scanf("%d",&num);

		int a = sqrt(num);
		int range = a/(world_size-1);
		int left = a - (range*(world_size-1));

		for(int i=1;i<world_size;i++)
		{
			int start, end;
			start = (i-1)*range + 1;
			if (i == 1 )
				start += 1;
			end = i*range;
			if (i == world_size -1)
				end += left;
			

			MPI_Send(&num,1,MPI_INT,i,1,MPI_COMM_WORLD);
			MPI_Send(&start,1,MPI_INT,i,2,MPI_COMM_WORLD);
			MPI_Send(&end,1,MPI_INT,i,3,MPI_COMM_WORLD);
		}
		int ans;
		int final_ans = 0;
		for(int i=1;i<world_size;i++)
		{
			MPI_Recv(&ans,1,MPI_INT,i,4,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
			if (ans == 1)
				final_ans = 1;
		}
		if (final_ans == 0)
			printf("%d is a prime.\n",num);
		else
			printf("%d is not a prime.\n",num);
	}

	else {
		int num, l, r;
		MPI_Recv(&num, 1, MPI_INT, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		MPI_Recv(&l, 1, MPI_INT, 0, 2, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		MPI_Recv(&r, 1, MPI_INT, 0, 3, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		int ans = 0;
		for (int i=l;i<=r && ans != 1;i++) {
			if (i==1 || i==0 || i==num)
				continue;
			if (num%i == 0)
				ans = 1;
		}
		MPI_Send(&ans,1,MPI_INT,0,4,MPI_COMM_WORLD);
	}
	
	MPI_Finalize();
	return 0;
}
