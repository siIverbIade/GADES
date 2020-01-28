import { request  } from 'graphql-request'

export class Con {
    static uri = 'http://localhost:8080/graphql'
    dados: Promise <any>
    constructor(query: string) {
        request(Con.uri, query).then(data =>
        this.dados = data
      )
    }
   
}