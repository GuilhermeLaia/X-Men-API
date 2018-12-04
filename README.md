# X-Men-API
API REST 

Execute Project API Spring Boot -> mvn spring-boot:run

SERVICES LOCAL:

POST → localhost:8088/api/mutant
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

GET -> localhost:8088/api/stats

SERVICES CLOUD HEROKU:

https://x-men-api.herokuapp.com


POST → https://x-men-api.herokuapp.com/api/mutant
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

GET -> https://x-men-api.herokuapp.com/api/stats
