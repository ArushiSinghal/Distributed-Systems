mpicc -g -Wall -o mpi_primes_sort mpi_primes_sort.c -lm

mpiexec -n no_of_processes ./mpi_primes_sort <n>

for c++ code

mpicc -g -Wall -o mpi_primes_sort mpi_primes_sort.c -lm
