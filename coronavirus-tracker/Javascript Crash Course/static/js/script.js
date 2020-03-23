// Challenge1
function ageInDays(){
var BithYear=prompt("What year were you born?")
var ageInDays=(2018-BithYear)*365;
var h1=document.createElement('h1');
var textAnswer=document.createTextNode('You are '+ageInDays+' days');
h1.setAttribute('id','ageInDays');
h1.appendChild(textAnswer);
document.getElementById('flex-box-result').appendChild(h1);
 
}
function reset(){
    document.getElementById('ageInDays').remove();
}
// Challenge2
function generateCat(){
var image=document.createElement('img');
var div=document.getElementById("flex-cat-gen");
image.src="http://thecatapi.com/api/images/get?format=src&type=gif&size=small";
div.appendChild(image)}
// Challenge 3 RPS
function rpsGame(yourChoice){
console.log(yourChoice);
var humanChoice, botChoice;
humanChoice=yourChoice.id;
//console.log(humanChoice);
botChoice=numberToChoice(randToRpsInt());
//console.log(botChoice)
results=decideWinner(humanChoice,botChoice);
//console.log(results);
message=finalMessage(results);
console.log(message);
rpsFrontend(yourChoice.id,botChoice,message);

}
function randToRpsInt(){
    return Math.floor(Math.random()*3);
}
function numberToChoice(number){
    return ['rock','paper','scrissors'][number];
}
function decideWinner(yourChoice,computerChoice){
 var rpsDatabase={
     'rock':
     {'scrissors':1,
        'rock':0.5,
        'paper':0
    },
     'paper':
     {'rock':1,
     'paper':0.5,
     'scrissors':0
    },
     'scrissors':
     {'paper':1,
     'scrissors':0.5,
     'rock':0
    }
 }   ;
 var yourScore=rpsDatabase[yourChoice][computerChoice];
 var computerScore=rpsDatabase[computerChoice][yourChoice];
 return [yourScore,computerScore];
}
function finalMessage([yourScore,computerScore]){
    if(yourScore===0){
        return {'message':'You lost!','color':'red'};

    }else if(yourScore===0.5){
        return {'message':'You tied!','color':'yellow'};
    }
    else{
        return {'message':'You won!','color':'green'};
    }
}
function rpsFrontend(humanImageChoice,botImageChoice,finalMessage){
    var imagesDatabase={
        'rock':document.getElementById('rock').src,
        'paper':document.getElementById('paper').src,
        'scrissors':document.getElementById('scrissors').src
    };
    //remove all the images
    document.getElementById('rock').remove();
    document.getElementById('paper').remove();
    document.getElementById('scrissors').remove();

    //div for every element 
    var humanDiv=document.createElement('div');
    var botDiv=document.createElement('div');
    var messageDiv=document.createElement('div');

    humanDiv.innerHTML="<img src='"+imagesDatabase[humanImageChoice]+"'height=150 width=150 style='box-shadow:0px 10px 50px rgba(37,50,233,1);'>"
    
    botDiv.innerHTML="<img src='"+imagesDatabase[botImageChoice]+"'height=150 width=150 style='box-shadow:0px 10px 50px rgba(243,38,24,1);'>"
    messageDiv.innerHTML="<h1 style='color: "+finalMessage['color']+"; font-size:60px; padding:30px;'>"+finalMessage['message']+"</h1>";
    document.getElementById('flex-box-rps-div').appendChild(humanDiv);
    document.getElementById('flex-box-rps-div').appendChild(messageDiv);
    document.getElementById('flex-box-rps-div').appendChild(botDiv);


 
}
/// Challenge 4: Change the color of all buttons
var all_buttons=document.getElementsByTagName('button');
var coppyAllButtons=[];
for(let i=0;i<all_buttons.length;i++){
    coppyAllButtons.push(all_buttons[i].classList[1]);
}
console.log(coppyAllButtons);
function buttonColorChange(buttonThingy){
    if(buttonThingy.value==='red'){
        buttonsRed();
    }else if(buttonThingy.value==='green'){
        buttonsGreen();
    }else if(buttonThingy.value==='reset'){
        buttonsColorReset();
    }else if(buttonThingy.value==='random'){
        buttonsRandomColor();
    }
}
function buttonsRed(){
    for(let i=0;i<all_buttons.length;i++){
        all_buttons[i].classList.remove(all_buttons[i].classList[1]);
        all_buttons[i].classList.add('btn-danger');
    }
}
function buttonsGreen(){
    for(let i=0;i<all_buttons.length;i++){
        all_buttons[i].classList.remove(all_buttons[i].classList[1]);
        all_buttons[i].classList.add('btn-success');
    }
}
function buttonsColorReset(){
    for(let i=0;i<all_buttons.length;i++){
        all_buttons[i].classList.remove(all_buttons[i].classList[1]);

    all_buttons[i].classList.add(coppyAllButtons[i])
}}
function buttonsRandomColor(){
    let choices=['btn-primary','btn-danger','btn-success','btn-warning'];
for(let i=0;i<all_buttons.length;i++){
    // inside the for loop! each color for the each buttons are selected random (not random same color for all the buttons!)
    var randomNumber=Math.floor(Math.random()*4);
    all_buttons[i].classList.remove(all_buttons[i].classList[1]);
    all_buttons[i].classList.add(choices[randomNumber]);
}

}
//Challenge5: Blackjack
let blackjackGame={
    'you':{'scoreSpan':'#your-blackjack-result','div':'#your-box','score':0},
    'dealer':{'scoreSpan':'#dealer-blackjack-result','div':'#dealer-box','score':0},
    'cards':['2','3','4','5','6','7','8','9','10','J','Q','K','A'],
    'cardsMap':{'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9,'10':10,'J':10,'Q':10,'K':10,'A':[1,10]},
    'wins':0,
    'loses':0,
    'draws':0,
    'isStand':false,
    'turnsOver':false
}

const YOU=blackjackGame['you']
const DEALER=blackjackGame['dealer']
const hitSound=new Audio('static/sounds/swish.m4a')
const winSound=new Audio('static/sounds/cash.mp3')
const lossSound=new Audio('static/sounds/aww.mp3')

document.querySelector('#blackjack-hit-button').addEventListener("click",blackjackHit);
document.querySelector('#blackjack-stand-button').addEventListener("click",dealerLogic);
document.querySelector("#blackjack-deal-button").addEventListener("click",blackjackDeal);
function blackjackHit(){
    if(blackjackGame['isStand']===false){
    let card=randomCard();
    console.log(card);
    showCard(YOU,card);
    updateScore(card,YOU);
    showScore(YOU); 
    }
}
function showCard(activePlayer,card){
    if(activePlayer['score']<=21){
    let cardImage=document.createElement('img');
    cardImage.src=`static/images/${card}.png`;
    document.querySelector(activePlayer['div']).appendChild(cardImage);
    hitSound.play();
}}
function blackjackDeal(){
    if(blackjackGame['turnsOver']){
        blackjackGame['isStand']=false;
    let yourImages=document.querySelector('#your-box').querySelectorAll('img');
    let dealerImages=document.querySelector('#dealer-box').querySelectorAll('img');
    console.log(yourImages);
    yourImages.forEach(item => {
        item.remove();
        
    });
    dealerImages.forEach(element => {
        element.remove();
    });
    computeWinner();
  
   document.querySelector('#your-blackjack-result').textContent=0;
   document.querySelector('#dealer-blackjack-result').textContent=0;
   document.querySelector('#your-blackjack-result').style.color='white';
   document.querySelector('#dealer-blackjack-result').style.color='white';
   document.querySelector('#blackjack-result').textContent="Let's play!";
   document.querySelector('#blackjack-result').style.color="black";
   YOU['score']=0;
   DEALER['score']=0;
   

blackjackGame['turnsOver']=false;
    }
    
}
function randomCard(){
    let randomIndex=Math.floor(Math.floor(Math.random()*13));
    return blackjackGame['cards'][randomIndex];
}
function updateScore(card,activePlayer){
    if(card==='A'){
    if(activePlayer['score']+blackjackGame['cardsMap'][card][1]<=21){
        activePlayer['score']+=blackjackGame['cardsMap'][card][1];
    }
    else{
        activePlayer['score']+=blackjackGame['cardsMap'][card][0];
    }}else{
    
    activePlayer['score']+=blackjackGame['cardsMap'][card]
}}
function showScore(activePlayer){
    if(activePlayer['score']>21){
        document.querySelector(activePlayer['scoreSpan']).textContent='BUST!';
        document.querySelector(activePlayer['scoreSpan']).style.color='red';
    }else if(activePlayer['score']===21){
        document.querySelector(activePlayer['scoreSpan']).textContent='BLACKJACK';
        document.querySelector(activePlayer['scoreSpan']).style.color='yellow';}
        else{

    document.querySelector(activePlayer['scoreSpan']).textContent=activePlayer['score']
    
}}
function sleep(ms){
    return new Promise(resolve=>setTimeout(resolve,ms));
}
 async function dealerLogic(){
    blackjackGame['isStand']=true;
    while(DEALER['score']<16&&blackjackGame['isStand']===true){
    let card=randomCard();
    showCard(DEALER,card);
    updateScore(card,DEALER);
   showScore(DEALER);
   await(sleep(1000))
    }

 
       blackjackGame['turnsOver']=true;
       let winner=computeWinner();
       showResult(winner);
   
    
}
//compute the winner and return who jsut won + update the wins,loses,draws
function computeWinner(){
    let winner;

    if(YOU['score']<=21)
    {
        if(YOU['score']>DEALER['score']||(DEALER['score']>21)){
            blackjackGame['wins']++;
            winner=YOU;
        }
        else if(YOU['score']<DEALER['score']){
            blackjackGame['loses']++;
            winner=DEALER;
        }
        else if(YOU['score']===DEALER['score']){
            blackjackGame['draws']++;
        }
     else if(YOU['score']>21&&DEALER['score']<=21){
        blackjackGame['loses']++;
        winner=DEALER;
    }
}
 else if(YOU['score']>21&&DEALER['score']>21){
    blackjackGame['draws']++;
}
console.log(blackjackGame)
return winner;
}
function showResult(winner){
    let message,messageColor;
    if(blackjackGame['turnsOver']){
    if(winner===YOU){
        document.querySelector('#wins').textContent=blackjackGame['wins'];
        message='You win!';
        messageColor='green';
        winSound.play();
    }
    else if(winner===DEALER){
        document.querySelector('#loses').textContent=blackjackGame['loses'];
        message='You lost!';
        messageColor='red';
        lossSound.play();
    }
    else{
        document.querySelector('#draws').textContent=blackjackGame['draws'];
        message='You drew!';
        messageColor='black';
    }
    document.querySelector('#blackjack-result').textContent=message;
    document.querySelector('#blackjack-result').style.color=messageColor;
}
}