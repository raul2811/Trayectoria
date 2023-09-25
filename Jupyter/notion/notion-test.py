import os
from notion_client import Client

notion = Client(auth=os.environ["NOTION_TOKEN"])   

# Realiza una solicitud para obtener la lista de bases de datos
databases = notion.databases.list()

# Itera sobre la lista de bases de datos e imprime sus nombres
for database in databases:
    print(f"Nombre de la base de datos: {database['title'][0]['plain_text']}")
    print(f"ID de la base de datos: {database['id']}")
    print("\n")