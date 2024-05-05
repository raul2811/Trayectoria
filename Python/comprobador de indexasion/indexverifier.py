import requests
from bs4 import BeautifulSoup
import urllib3

def verificar_indexador_por_eissn(eissn, indexador):
    if indexador.lower() == 'latinindex':
        url = f'https://www.latindex.org/latindex/Solr/Busqueda?idModBus=0&buscar={eissn}&submit=Buscar'
        
        # Desactivar la verificación del certificado SSL
        urllib3.disable_warnings()
        
        response = requests.get(url, verify=False)
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            tabla_resultados = soup.find('table', id='table-responsive')
            filas = tabla_resultados.find_all('tr')
            for fila in filas:
                # Encuentra todas las celdas de la fila
                celdas = fila.find_all('td')
                # Verifica que haya al menos una celda en la fila
                if celdas:
                    # Comprueba si la celda e-ISSN contiene el eISSN buscado
                    celda_eissn = celdas[3]
                    if eissn in celda_eissn.text.strip():
                        journal_title = fila.find('a').text.strip()
                        return f"La revista '{journal_title}' está indexada en Latindex."
            return "El e-ISSN no está en la tabla de resultados."
        else:
            return "No se pudo conectar con Latindex. Verifica tu conexión a internet."
    else:
        return "Indexador no válido. Por favor, ingresa 'latinindex'."
    
def verificar_indexador_por_issn(issn, indexador):
    if indexador.lower() == 'latinindex':
        url = f'https://www.latindex.org/latindex/Solr/Busqueda?idModBus=0&buscar={issn}&submit=Buscar'
        
        # Desactivar la verificación del certificado SSL
        urllib3.disable_warnings()
        
        response = requests.get(url, verify=False)
        if response.status_code == 200:
            soup = BeautifulSoup(response.content, 'html.parser')
            tabla_resultados = soup.find('table', id='table-responsive')
            filas = tabla_resultados.find_all('tr')
            for fila in filas:
                # Encuentra todas las celdas de la fila
                celdas = fila.find_all('td')
                # Verifica que haya al menos una celda en la fila
                if celdas:
                    # Comprueba si la celda e-ISSN contiene el eISSN buscado
                    celda_issn = celdas[4]
                    if issn in celda_issn.text.strip():
                        journal_title = fila.find('a').text.strip()
                        return f"La revista '{journal_title}' está indexada en Latindex."
            return "El ISSN no está en la tabla de resultados."
        else:
            return "No se pudo conectar con Latindex. Verifica tu conexión a internet."
    else:
        return "Indexador no válido. Por favor, ingresa 'latinindex'."
       
eissn= "2697-3294"
issn= "0210-2323"
resultado_eissn = verificar_indexador_por_eissn(eissn, "latinindex")
resultado_issn = verificar_indexador_por_issn(issn, "latinindex")
print(resultado_eissn,"\n",resultado_issn)