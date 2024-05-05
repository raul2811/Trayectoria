import requests
from bs4 import BeautifulSoup
import urllib3

def verificar_indexador_por_eissn(eissn, indexador):
    try:
        # Verificar si el indexador es válido
        if indexador.lower() == 'latinindex':
            # Construir la URL de búsqueda con el e-ISSN
            url = f'https://www.latindex.org/latindex/Solr/Busqueda?idModBus=0&buscar={eissn}&submit=Buscar'

            # Desactivar la verificación del certificado SSL
            urllib3.disable_warnings()

            # Realizar la solicitud GET a la URL
            with requests.get(url, verify=False) as response:
                # Verificar si la respuesta es exitosa (código 200)
                if response.status_code == 200:
                    # Parsear la respuesta HTML
                    soup = BeautifulSoup(response.content, 'html.parser')
                    # Encontrar la tabla de resultados
                    tabla_resultados = soup.find('table', id='table-responsive')
                    # Obtener todas las filas de la tabla
                    filas = tabla_resultados.find_all('tr')
                    # Iterar sobre las filas
                    for fila in filas:
                        # Encontrar todas las celdas de la fila
                        celdas = fila.find_all('td')
                        # Verificar si hay al menos una celda en la fila
                        if celdas:
                            # Obtener la celda del e-ISSN
                            celda_eissn = celdas[3]
                            # Verificar si el e-ISSN buscado está en la celda
                            if eissn in celda_eissn.text.strip():
                                # Obtener el título de la revista
                                journal_title = fila.find('a').text.strip()
                                return f"La revista '{journal_title}' está indexada en Latindex."
                    # Si no se encontró el e-ISSN en la tabla de resultados
                    return "El e-ISSN no está en la tabla de resultados."
                else:
                    # Si la solicitud no fue exitosa
                    return "No se pudo conectar con Latindex. Verifica tu conexión a internet."
        else:
            # Si el indexador no es válido
            return "Indexador no válido. Por favor, ingresa 'latinindex'."
    except Exception as e:
        # Capturar cualquier excepción y retornar un mensaje de error
        return f"Error: {e}"
    finally:
        # Revertir el desactivar las advertencias de urllib3
        urllib3.disable_warnings()

# Función para verificar indexador por ISSN
def verificar_indexador_por_issn(issn, indexador):
    try:
        if indexador.lower() == 'latinindex':
            url = f'https://www.latindex.org/latindex/Solr/Busqueda?idModBus=0&buscar={issn}&submit=Buscar'
            urllib3.disable_warnings()
            with requests.get(url, verify=False) as response:
                if response.status_code == 200:
                    soup = BeautifulSoup(response.content, 'html.parser')
                    tabla_resultados = soup.find('table', id='table-responsive')
                    filas = tabla_resultados.find_all('tr')
                    for fila in filas:
                        celdas = fila.find_all('td')
                        if celdas:
                            celda_issn = celdas[4]
                            if issn in celda_issn.text.strip():
                                journal_title = fila.find('a').text.strip()
                                return f"La revista '{journal_title}' está indexada en Latindex."
                    return "El ISSN no está en la tabla de resultados."
                else:
                    return "No se pudo conectar con Latindex. Verifica tu conexión a internet."
        else:
            return "Indexador no válido. Por favor, ingresa 'latinindex'."
    except Exception as e:
        return f"Error: {e}"
    finally:
        urllib3.disable_warnings()

           
eissn= "2697-3294"
issn= "0210-2323"
resultado_eissn = verificar_indexador_por_eissn(eissn, "latinindex")
resultado_issn = verificar_indexador_por_issn(issn, "latinindex")
print(resultado_eissn,"\n",resultado_issn)