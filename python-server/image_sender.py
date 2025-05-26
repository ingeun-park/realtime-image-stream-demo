import socket
import time
import struct
from PIL import Image
import io

def get_dummy_jpeg():
    img = Image.new('RGB', (320, 240), color=(255, 0, 0))
    buffer = io.BytesIO()
    img.save(buffer, format='JPEG')
    return buffer.getvalue()

HOST, PORT = '0.0.0.0', 9999

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen(1)
    print(f"Listening on {HOST}:{PORT}...")
    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        while True:
            jpeg = get_dummy_jpeg()
            conn.sendall(struct.pack('>I', len(jpeg)) + jpeg)
            time.sleep(0.1)  # 10 FPS
