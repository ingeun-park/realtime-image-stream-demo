# Image Streaming Protocol

- Client connects via TCP
- Server sends: [4-byte length][JPEG image bytes]
- No ack/response required
