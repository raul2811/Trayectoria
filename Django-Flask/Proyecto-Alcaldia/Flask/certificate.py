from cryptography import x509
from cryptography.x509.oid import NameOID
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives.serialization import Encoding, PrivateFormat, NoEncryption
from datetime import datetime, timedelta

# Generar una clave privada RSA
private_key = rsa.generate_private_key(
    public_exponent=65537,
    key_size=2048
)

# Crear el certificado autofirmado
subject = issuer = x509.Name([
    x509.NameAttribute(NameOID.COUNTRY_NAME, 'US'),
    x509.NameAttribute(NameOID.STATE_OR_PROVINCE_NAME, 'California'),
    x509.NameAttribute(NameOID.LOCALITY_NAME, 'San Francisco'),
    x509.NameAttribute(NameOID.ORGANIZATION_NAME, 'Mi Organización'),
    x509.NameAttribute(NameOID.COMMON_NAME, 'localhost')
])

cert = x509.CertificateBuilder().subject_name(subject).issuer_name(issuer).public_key(
    private_key.public_key()
).serial_number(
    x509.random_serial_number()
).not_valid_before(
    datetime.utcnow()
).not_valid_after(
    datetime.utcnow() + timedelta(days=365)
).sign(private_key, hashes.SHA256())

# Guardar la clave privada y el certificado en archivos
with open('key.pem', 'wb') as f:
    f.write(private_key.private_bytes(
        Encoding.PEM,
        PrivateFormat.PKCS8,
        NoEncryption()
    ))

with open('cert.pem', 'wb') as f:
    f.write(cert.public_bytes(Encoding.PEM))
