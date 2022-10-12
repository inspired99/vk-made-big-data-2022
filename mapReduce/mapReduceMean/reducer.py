#!/usr/bin/python3

import sys


def main():
    total_sum = 0
    total_chunk_size = 0

    for line in sys.stdin:
        line = line.strip()
        chunk_size, mean = [float(i) for i in line.split('\t')]
        total_sum += mean * chunk_size
        total_chunk_size += chunk_size

    total_mean = 0
    if total_chunk_size:
        total_mean = total_sum / total_chunk_size
    print(total_mean)


if __name__ == "__main__":
    main()
