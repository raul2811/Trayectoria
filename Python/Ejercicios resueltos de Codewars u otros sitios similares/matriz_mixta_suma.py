def sum_mix(arr):
    arr_final=0
    for i in range (len(arr)):
        arr_str=str(arr[i])
        arr_int=int(arr_str)
        arr_final=arr_int+arr_final
    return  arr_final

"""

solucion optima:
def sum_mix(arr):
    return sum(map(int, arr))
    
"""