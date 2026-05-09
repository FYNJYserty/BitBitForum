FROM golang:1.26.0
WORKDIR /app
COPY . .
RUN go build -o main .
EXPOSE 9090
CMD ["./main"]