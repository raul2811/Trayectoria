import pyautogui
import time
from pynput import mouse, keyboard
import threading

# Variable global para controlar la ejecución del programa
program_running = True

def autoclicker():
    while program_running:  # Asumiendo que 'program_running' es una variable definida en otro lugar
        pyautogui.click()  # Realiza un clic en la ubicación actual del mouse
        print("Clic realizado. Esperando 60 segundos...")
        time.sleep(1)  # Espera 60 segundos (un minuto) entre clics

def on_press(key):
    global program_running
    if key == keyboard.Key.esc:  # Si se presiona la tecla 'esc', detiene el programa
        program_running = False

def tomar_captura(x, y, ancho, alto):   
    global program_running
    # Variable para numerar las capturas
    num_captura = 1

    def on_click(x, y, button, pressed):
        nonlocal num_captura  # Accede a la variable en el ámbito externo
        if button == mouse.Button.left and pressed:
            # Toma una captura de pantalla de la región específica
            captura = pyautogui.screenshot(region=(x_fijo, y_fijo, ancho, alto))

            # Guarda la captura numerada
            captura.save(f"./Tomar captura de manera automatica/capturas/captura_{str(num_captura).zfill(2)}.png")
            print(f"Captura {num_captura} tomada.")

            # Incrementa el contador de capturas
            num_captura += 1

    # Esperar 10 segundos antes de comenzar a escuchar los clics
    print("Esperando 10 segundos antes de comenzar...")
    time.sleep(10)
    print("Comenzando a escuchar clics...")

    # Crear un listener para la tecla 'esc'
    with keyboard.Listener(on_press=on_press):
        try:
            # Iniciar la escucha del clic izquierdo del mouse
            with mouse.Listener(on_click=on_click) as listener:
                listener.join()
        except KeyboardInterrupt:
            print("Programa detenido con Ctrl+C")
            program_running = False

# Coordenadas y dimensiones del área que quieres capturar
x_fijo = 12
y_fijo = 55
ancho = 1250
alto = 866

# Iniciar el hilo del autoclicker antes de iniciar la captura
threading.Thread(target=autoclicker).start()

# Llamar a la función para comenzar a tomar capturas con el clic izquierdo
tomar_captura(x_fijo, y_fijo, ancho, alto)