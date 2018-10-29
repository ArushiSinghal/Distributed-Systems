mpicc -g -Wall -o mpi_primes_sort mpi_primes_sort.c -lm

mpiexec -n <p> ./mpi_primes_sort <n>

for c++ code

mpicc -g -Wall -o mpi_primes_sort mpi_primes_sort.c -lm
