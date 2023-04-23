# Cinemasofona
1. Aplicação móvel desenvolvida em Android Nativo na linguagem Kotlin para a disciplina de 
Computação móvel, com o .apk testado no Pixel 6 Pro API 23.
2. Esta aplicação foi desenvolvida e teve por base o nosso protótipo desenvolvido na disciplina de
IHM, que pode ser encontrado aqui:
 * link: https://e535pj.axshare.com/#id=a47ouu&p=main_page&g=1

## Dados de aluno

Nome: João Pedro Matos <br />
Número: a22202497<br />
Nome: Ricardo Gonçalves <br />
Número: a22000492<br />


## Screenshots dos ecrãs

### Dashboard Screen (1): <br> <br> <img src="images/Screenshot_dashboard.png" height="50%" width="50%">
### Movies Screen (2): <br> <br> <img src="images/Screenshot_movies.png" height="50%" width="50%">
### Movie Register Screen (1): <br> <br> <img src="images/Screenshot_register1.png" height="50%" width="50%">
### Movie Register Screen (2): <br> <br> <img src="images/Screenshot_register2.png" height="50%" width="50%">
### Movie Register Screen (3): <br> <br> <img src="images/Screenshot_register3.png" height="50%" width="50%">
### Details Screen (1): <br> <br> <img src="images/Screenshot_details_1.png" height="50%" width="50%">
### Details Screen (2): <br> <br> <img src="images/Screenshot_details_2.png" height="50%" width="50%">
### Details Screen (3): <br> <br> <img src="images/Screenshot_details3.png" height="50%" width="50%">
### Share (1): <br> <br> <img src="images/Screenshot_share1.png" height="50%" width="50%">
### Share (2): <br> <br> <img src="images/Screenshot_share2.png" height="50%" width="50%">
### Voice (1): <br> <br> <img src="images/Screenshot_voice.png" height="50%" width="50%">

## Nomes dos filmes em hardcoded (movies.json)
* The Shawshank Redemption
* The Godfather
* The Dark Knight
* Schindler's List
* The Lord of the Rings: The Return of the King

## Funcionalidades
### Table: <br> <br> <img src="images/table.png">

Das funcionalidades mencionadas na tabela anterior, foram implementadas todas excepto a rotação da lista de filmes.


## Idiomas (Multi-language)
* Inglês (default)
<br> <br> <img src="images/Screenshot_register1.png" height="50%" width="50%">
* Português
<br> <br> <img src="images/Screenshot_language1.png" height="50%" width="50%">
* Espanhol
<br> <br> <img src="images/Screenshot_language2.png" height="50%" width="50%">


## Autoavaliação
Nota: 18 valores

## Classes e atributos
**Classe Movie:**
 -Atributos:
    * id - Int,
    * name - String,
    * photo - String,
    * genre - String,
    * synopsis - String,
    * releaseDate - String,
    * imdbRating - Double,
    * imdbLink - String;
  -Métodos:
    * getId() - Int,
    * getName() - String,
    * getGenre() - String,
    * getSynopsis() - String,
    * getReleaseDateString() - String,
    * getImdbRating() - Double,
    * getReleaseDate() - Date,
    * getImdbLink() - String;

**Classe MovieRegistry:**
  -Atributos:
    * movieId - Int,
    * cinema - String,
    * rate - Int,
    * seen - String,
    * observations - String,
    * images - List<Uri>;
  -Métodos:
    * getMovieId() - Int,
    * getCinema() - String,
    * getRate() - Int,
    * getSeen() - String,
    * getObservations() - String,
    * getImages() - List<Uri>;

**Classe History:**
  -Atributos:
    * registryList - List<MovieRegistry>;
  -Métodos:
    * loadMovies() - List<Movie>,
    * getMovieById() - Movie?,
    * getMovieByName() - Movie?,
    * getRegistryByMovieId() - MovieRegistry?,
    * saveRegistry() - void,
    * top5ImdbMovies() - List<Movie>,
    * top5BestRatedMovies() - List<Movie>,
    * top5LastSeenMovies() - List<Movie>;

## Referências
Para as funcionalidades que não foram dadas em aula utilizámos apenas o ChatGPT. 

![ChatGPT - Clear Form](https://i.gyazo.com/73a2b2d49ee2099e9135e74d0aaedb12.png)

![ChatGPT - Diaglo com countdown](https://i.gyazo.com/37c4936af2f9b5a3a33aa750b743a130.png)

![ChatGPT - Params em strings](https://i.gyazo.com/f417b7226169514d0e5fd8407063d1a5.png)

![ChatGPT - Delete de imagens](https://i.gyazo.com/ae55c5adf028689739d7e07f5d806b1d.png)

![ChatGPT - Upload de imagens 1](https://i.gyazo.com/8e6ce940ac26436444c86e919a335b69.png)

![ChatGPT - Upload de imagens 2](https://i.gyazo.com/fe3a2b747457249dac840f44ec9d59ef.png)

![ChatGPT - Upload de imagens 3](https://i.gyazo.com/7fb75ee511a673a5a18041545e1e1575.png)

![ChatGPT - Hint em itálico](https://i.gyazo.com/a6b4624de2e42056f0f681ea632488a7.png)

![ChatGPT - Ler JSON](https://i.gyazo.com/a30d33eeb79c97b4d1440ef531b3b90e.png)