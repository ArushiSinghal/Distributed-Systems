mpicc -g -Wall -o <mpi_primes_sort> <mpi_primes_sort.c> -lm
mpiexec -n <p> ./<mpi_primes_sort>

for c++ code
mpicc -g -Wall -o <mpi_primes_sort> <mpi_primes_sort.c> -lm
