/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
    if (!document.getElementById) {
        return;
    } // To be nice to old browsers
    tempo(1);
    var game1 = new solitaireGame('solitaire1', document.getElementById('jogo'));
    game1.addImagePack('cardsets/h36/', [['walk', 'Mountain walk'], ['midnightsun', 'Midnight sun']], '.png', 25, 36, 'Northern Norway');
    game1.addImagePack('cardsets/h96/', [['bluepattern', 'Blue pattern'], ['blue', 'Plain blue'], ['x', 'Red X']], '.gif', 71, 90, 'Once in a Lifetime');
    game1.addImagePack('cardsets/cartas/', [['fundo', 'Fundo']], '.png', 105, 150, 'Main set');
    game1.setCheatMode(false);
    game1.startGame();
};