

def count_sheeps(sheep):
    cantidad=0
    long=len(sheep)
    for i in range (long):
        if sheep[i] == True:
            cantidad=cantidad+1
            print (cantidad)
    return cantidad



array1 = [True,  True,  True,  False,
                  True,  True,  True,  True ,
                  True,  False, True,  False,
                  True,  False, False, True ,
                  True,  True,  True,  True ,
                  False, False, True,  True ];


count_sheeps(array1)

'''
otra solucion 

def count_sheeps(arrayOfSheeps):
  return arrayOfSheeps.count(True)
  
'''