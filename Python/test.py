import json
import time
import sys

# Nombre del archivo JSON donde se almacenará la información
archivo_json = "estado_programa.json"

# Verificar si el archivo JSON existe
try:
    with open(archivo_json, "r") as archivo:
        estado_programa = json.load(archivo)
except FileNotFoundError:
    # Si el archivo no existe, crea uno con valores iniciales
    estado_programa = {
        "combinaciones_generadas": [],
        "numero_actual": 0,
        "numero_final": 91300063
    }

# Función para generar las combinaciones y guardar el progreso
def generar_combinaciones():
    combinaciones_generadas = estado_programa["combinaciones_generadas"]
    numero_actual = estado_programa["numero_actual"]
    numero_final = estado_programa["numero_final"]

    # Inicia el temporizador
    inicio = time.time()

    try:
        while numero_actual <= numero_final:
            # Genera la combinación actual
            combinacion_actual = f"WiFi-{numero_actual:08d}"
            
            # Agrega la combinación actual a la lista
            combinaciones_generadas.append(combinacion_actual)

            # Incrementa el número actual
            numero_actual += 1

            # Imprime el progreso en pantalla
            print(f"Combinación actual: {combinacion_actual}", end="\r")
            sys.stdout.flush()  # Para actualizar la impresión en tiempo real

    except KeyboardInterrupt:
        # Si se interrumpe el proceso, guarda el progreso en el archivo JSON
        estado_programa["combinaciones_generadas"] = combinaciones_generadas
        estado_programa["numero_actual"] = numero_actual

        with open(archivo_json, "w") as archivo:
            json.dump(estado_programa, archivo)
        
        print("\nProceso interrumpido. El progreso ha sido guardado.")

    # Finaliza el temporizador
    fin = time.time()

    # Calcula el tiempo transcurrido
    tiempo_transcurrido = fin - inicio

    # Actualiza el estado del programa en el archivo JSON
    estado_programa["combinaciones_generadas"] = combinaciones_generadas
    estado_programa["numero_actual"] = numero_actual

    with open(archivo_json, "w") as archivo:
        json.dump(estado_programa, archivo)

    return tiempo_transcurrido

# Ejecuta la generación de combinaciones y obtén el tiempo transcurrido
tiempo_transcurrido = generar_combinaciones()

print(f"Tarea completada en {tiempo_transcurrido:.2f} segundos.")
