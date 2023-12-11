#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>

#define ARCHIVO_BASE "estado_programa_temp"
#define TAM_BUFFER 1000 // Tamaño del buffer
#define NUM_HILOS 4     // Número de hilos

struct DatosHilo {
    int inicio;
    int final;
};
int cantidadCombinaciones = 408699936; // Cantidad de combinaciones posibles
pthread_mutex_t lock; // Mutex para controlar el acceso al archivo

void *generarCombinaciones(void *parametro) {
    struct DatosHilo *datos = (struct DatosHilo *)parametro;

    FILE *archivo;
    char buffer[TAM_BUFFER][15]; // Buffer para almacenar las combinaciones
    int numero_actual = datos->inicio;
    const int numero_final = datos->final;

    char nombreArchivo[50];
    sprintf(nombreArchivo, "%s_%d.txt", ARCHIVO_BASE, datos->inicio);

    archivo = fopen(nombreArchivo, "w"); // Abre un archivo temporal para escritura
    if (archivo == NULL) {
        printf("No se pudo abrir el archivo.");
        return NULL;
    }

    int i = 0;
    while (numero_actual <= numero_final) {
        // Genera la combinación actual
        sprintf(buffer[i], "WiFi-%08d\n", numero_actual);

        i++;
        numero_actual++;

        if (i == TAM_BUFFER || numero_actual > numero_final) {
            // Bloquea el acceso al archivo
            pthread_mutex_lock(&lock);

            // Escribe el contenido del buffer en el archivo temporal
            fwrite(buffer, sizeof(char), i * 15, archivo);

            // Desbloquea el acceso al archivo
            pthread_mutex_unlock(&lock);

            i = 0; // Reinicia el índice del buffer
        }
    }

    fclose(archivo);
    return NULL;
}


void combinarArchivosTemporales() {
    FILE *archivoFinal = fopen("estado_programa.txt", "w");
    if (archivoFinal == NULL) {
        printf("No se pudo abrir el archivo final.");
        return;
    }

    for (int i = 0; i < NUM_HILOS; i++) {
        char nombreArchivo[50];
        sprintf(nombreArchivo, "%s_%d.txt", ARCHIVO_BASE, i * (cantidadCombinaciones / NUM_HILOS));

        FILE *archivoTemp = fopen(nombreArchivo, "r");
        if (archivoTemp == NULL) {
            printf("No se pudo abrir el archivo temporal.");
            return;
        }

        char linea[15];
        while (fgets(linea, sizeof(linea), archivoTemp) != NULL) {
            fputs(linea, archivoFinal);
        }

        fclose(archivoTemp);
        remove(nombreArchivo); // Elimina el archivo temporal
    }

    fclose(archivoFinal);
}

int main() {
    pthread_t hilos[NUM_HILOS];
    struct DatosHilo datos[NUM_HILOS];

    pthread_mutex_init(&lock, NULL); // Inicializa el mutex

    clock_t inicio = clock();

    int numerosPorHilo = 1 + (cantidadCombinaciones/ NUM_HILOS);
    for (int i = 0; i < NUM_HILOS; i++) {
        datos[i].inicio = i * numerosPorHilo;
        datos[i].final = (i + 1) * numerosPorHilo - 1;
        if (datos[i].final > cantidadCombinaciones) {
            datos[i].final = cantidadCombinaciones;
        }
        pthread_create(&hilos[i], NULL, generarCombinaciones, &datos[i]);
    }

    // Barra de progreso
    int progress = 0;
    printf("Progreso: [");
    while (progress < 100) {
        printf("%d%%", progress);
        fflush(stdout);
        usleep(1000000); // Pausa de 1 segundo (ajustar según sea necesario)
        printf("\b\b\b");
        progress++;
    }
    printf("]");

    // Espera a que todos los hilos hayan terminado
    for (int i = 0; i < NUM_HILOS; i++) {
        pthread_join(hilos[i], NULL);
    }

    combinarArchivosTemporales();

    pthread_mutex_destroy(&lock); // Destruye el mutex

    clock_t fin = clock();
    double tiempo_transcurrido = ((double)(fin - inicio)) / CLOCKS_PER_SEC;
    
    printf("\nTarea completada con paralelización.\n");
    printf("Tiempo transcurrido: %.2f segundos\n", tiempo_transcurrido);

    return 0;
}