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

### Dashboard Screen (1): <br> <br> <img src="https://i.gyazo.com/5b647603adebc48f0e76cc19456639f2.png" height="50%" width="50%">
### Movies Screen List (2): <br> <br> <img src="https://i.gyazo.com/962280a6bb85cc73bca20fa1f60f6232.png" height="50%" width="50%">
### Movies Screen Map (2): <br> <br> <img src="https://i.gyazo.com/86b34e641dbcbda3b8691c7f7618f21d.png" height="50%" width="50%">
### Movie Register Screen (1): <br> <br> <img src="https://i.gyazo.com/c7d09f334bee7e307f115bf8e4031b54.png" height="50%" width="50%">
### Movie Register Screen (2): <br> <br> <img src="https://i.gyazo.com/9629adcbf961b38760d15f269eec073b.png" height="50%" width="50%">
### Movie Register Screen (3): <br> <br> <img src="https://i.gyazo.com/d141d17e2262572dff93e88c53fb7421.png" height="50%" width="50%">
### Details Screen (1): <br> <br> <img src="https://i.gyazo.com/5978ee4970d8c3c86c432658bee97522.png" height="50%" width="50%">
### Details Screen (2): <br> <br> <img src="https://i.gyazo.com/79d24aaac08638690bd4fe0f009a7ed4.png" height="50%" width="50%">
### Share (1): <br> <br> <img src="images/Screenshot_share1.png" height="50%" width="50%">
### Share (2): <br> <br> <img src="images/Screenshot_share2.png" height="50%" width="50%">
### Voice (1): <br> <br> <img src="https://i.gyazo.com/e9f861a44609e16e87173400e69d0419.png" height="50%" width="50%">
### Voice (2): <br> <br> <img src="https://i.gyazo.com/0756bfb2e5f0d005d0ee50ffcd4e7527.png" height="50%" width="50%">
### Voice (3): <br> <br> <img src="https://i.gyazo.com/2cf0ecec448a641c7d4e7c4a6b2d7231.png" height="50%" width="50%">


## Funcionalidades
### Table: <br> <br> <img src="https://i.gyazo.com/ba91986387de490bf292459531a9cca9.png">

Das funcionalidades mencionadas na tabela anterior, foram implementadas todas excepto o extra.


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
    * id - String,
    * name - String,
    * year - String,
    * photo - String,
    * genre - String,
    * synopsis - String,
    * releaseDate - String,
    * imdbRating - Double,
    * imdbLink - String;
  -Métodos:
    * toMovieDB() - MovieDB;

**Classe MovieRegistry:**
  -Atributos:
    * id - Long,
    * movie - Movie
    * cinema - Cinema,
    * rate - Int,
    * seen - String,
    * observations - String,
    * images - List<RegistryImage>;
  -Métodos:
    * toMovieRegistryDB() - MovieRegistryDB,
    * rateColor() - Float;

**Classe History:**
  -Métodos:
    * loadCinemas() - List<Cinema>,
    * getCinemaByName() - Cinema?;

**Classe Cinema:**
    -Atributos:
        * id - Long,
        * name - String,
        * provider - String,
        * address - String,
        * latitude - Double,
        * longitude - Double,
        * county - String;
    -Métodos:
        * toCinemaDB() - CinemaDB;

**Classe RegistryImage:**
    -Atributos:
        * id - Long,
        * uri - String,
        * movieRegistryId - Long;
    -Métodos:
        * toRegistryImageDB() - RegistryImageDB;

**Class MarkerData:**
    -Atributos:
        * marker - Marker,
        * registryId - Long;
    -Métodos:
        * toMarkerDataDB() - MarkerDataDB;

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