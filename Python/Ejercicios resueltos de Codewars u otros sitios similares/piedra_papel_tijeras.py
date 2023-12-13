'''
                ENUNCIADO
------------------------------------------

¡Vamos a jugar! ¡Tienes que devolver qué jugador ganó! En caso de empate se regresa Draw!.

Ejemplos (Entrada1, Entrada2 --> Salida):

"scissors", "paper" --> "Player 1 won!"
"scissors", "rock" --> "Player 2 won!"
"paper", "paper" --> "Draw!"

------------------------------------------- 
'''

def rps(p1, p2):
    print(f"Selección del jugador 1: {p1}\nSelección del jugador 2: {p2}")
    
    if (p1 == "rock" and p2 == "scissors") or \
       (p1 == "paper" and p2 == "rock") or \
       (p1 == "scissors" and p2 == "paper"):
        print ('Player 1 won!')
        return 'Player 1 won!'
    elif (p2 == "rock" and p1 == "scissors") or \
         (p2 == "paper" and p1 == "rock") or \
         (p2 == "scissors" and p1 == "paper"):
        print ('Player 2 won!')
        return 'Player 2 won!'
    else:
        return 'Draw!'
'''
Otra forma de hacerlos es :

def rps(p1, p2):
    beats = {'rock': 'scissors', 'scissors': 'paper', 'paper': 'rock'}
    if beats[p1] == p2:
        return "Player 1 won!"
    if beats[p2] == p1:
        return "Player 2 won!"
    return "Draw!"

Este código implementa el juego de Piedra, Papel o Tijeras en Python utilizando un 
enfoque basado en diccionarios. Aquí está la explicación línea por línea:

def rps(p1, p2):: Define una función llamada rps que toma dos argumentos, p1 y p2, 
que representan las elecciones de los jugadores.

beats = {'rock': 'scissors', 'scissors': 'paper', 'paper': 'rock'}: Crea un diccionario 
llamado beats que asigna cada opción del juego ('rock', 'scissors', 'paper') a la opción que vence a la 
clave correspondiente. Por ejemplo, 'rock' vence a 'scissors', 'scissors' vence a 'paper' y 'paper' vence a 'rock'.

if beats[p1] == p2:: Verifica si la opción p1 del jugador 1 vence a la opción p2 del 
jugador 2 según las reglas del juego. Si es así, significa que el jugador 1 gana, por lo que devuelve "Player 1 won!".

if beats[p2] == p1:: Similar al paso anterior, esta línea verifica si la opción p2 del 
jugador 2 vence a la opción p1 del jugador 1. Si es así, el jugador 2 gana y devuelve "Player 2 won!".

return "Draw!": Si ninguna de las condiciones anteriores se cumple, significa que es un 
empate, por lo que devuelve "Draw!".
'''    