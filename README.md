# Real-time Image Streaming Demo

본 프로젝트는 **실제 의료기기 장비 납품 시스템 구조**를 단순화하여 구현한 데모입니다.  
Raspberry Pi 카메라 모듈에서 실시간 JPEG 이미지를 Java 기반 GUI로 전송/렌더링하는 구조를 기반으로 하고 있으며,  
**Python + Java 간 영상 스트리밍 통신**을 어떻게 구성할 수 있는지를 보여줍니다.

---

## 시스템 구조

```text
[ Python 서버 ]
  |
  | (Socket, JPEG Byte Stream)
  |
[ Java GUI 클라이언트 ]
```

- Python: Dummy JPEG 생성 → TCP 전송 (10 FPS)
- Java: Socket 수신 → BufferedImage 변환 → JLabel에 표시

---

## 사용 기술

| 구성 요소 | 기술 |
|-----------|------|
| 통신 | TCP Socket, struct (byte stream) |
| 영상 처리 | PIL (Python), ImageIO (Java) |
| GUI | Java Swing, JLabel |
| 쓰레드 처리 | Java 수신 스레드 → Swing UI 스레드 분리 처리 |

---

## 실제 사용처

해당 구조는 **젤이미징시스템 Gel imaging System**의 핵심 구조를 기반으로 구성되었습니다.

- **Raspberry Pi 5 + Python (picamera2)**로 JPEG 압축 후 전송
- Java GUI는 실시간 영상 수신, 장비 명령 제어 및 이벤트 반영 UI 포함
- 실제 의료기기 (세브란스 외 납품 장비)에 사용된 시스템을 축소/가공

>  본 코드는 실제 상용 코드와는 다르며, **구조와 원리를 설명하기 위한 데모 버전**입니다.

---

## ▶ 실행 방법

### 1. Python 서버 실행

```bash
cd python-server
python image_sender.py
```

### 2. Java 클라이언트 실행

```bash
cd java-client
javac ImageClient.java
java ImageClient
```

---
