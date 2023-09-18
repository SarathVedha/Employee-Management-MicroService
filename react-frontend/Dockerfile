FROM node:latest

LABEL authors="sarath"

WORKDIR /app

COPY public/ /app/public
COPY src/ /app/src
COPY package.json /app/

RUN npm install

ENTRYPOINT ["npm", "start"]