#!/usr/bin/python3

import sys


def main():
    cur_chunk_size = 0
    cur_mean = 0
    cur_var = 0

    for line in sys.stdin:
        line = line.strip()
        chunk_size, mean, var = [float(i) for i in line.split('\t')]
        cur_var = ((cur_var * cur_chunk_size + chunk_size * var) / (cur_chunk_size + chunk_size)) + cur_chunk_size \
                  * chunk_size * ((cur_mean - mean) / (cur_chunk_size + chunk_size)) ** 2
        cur_mean = (cur_chunk_size * cur_mean + chunk_size * mean) / (cur_chunk_size + chunk_size)
        cur_chunk_size += chunk_size

    print(cur_var)


if __name__ == "__main__":
    main()
