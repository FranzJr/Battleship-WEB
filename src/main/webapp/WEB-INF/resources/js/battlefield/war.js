
$( document ).ready(function() {
    var fields = JSON.parse($('#ships-input').val());
    var fieldsOpponent = new Array();
    
    createBattlefield('battlefieldOpp', true);
    createBattlefield('battlefield', false);
    
    function createBattlefield(battlefieldID, opp){
    	var count = 0;
    	
    	if(opp){
            $('.container.opp').append('<div id="'+battlefieldID+'"></div>');    
    	}else{
            $('.container.player').append('<div id="'+battlefieldID+'"></div>');    
    	}

        var $battlefield = $('#'+battlefieldID);


        for(var i = 0; i < 10 ; i++){

            $battlefield.append('<div id="row_'+i+battlefieldID+'" class="row"></div>');    
            var $battlefieldROW = $("#row_"+i+battlefieldID);
            
            for(var j = 0; j < 10 ; j++){
                

                var field = {
                    "x": i,
                    "y": j,
                    "ship": false
                };
                if (opp) {
                    $battlefieldROW.append('<div class="fieldOpponent col" data-id="'+count+'" data-x="'+i+'" data-y="'+j+'"></div>');    
                	fieldsOpponent[count] = field;
				}else{
					field = fields[count];
					if(field.ship){
						$battlefieldROW.append('<div class="field col ship" data-id="'+count+'" data-x="'+i+'" data-y="'+j+'"></div>');    
					}else{
	                    $battlefieldROW.append('<div class="field col" data-id="'+count+'" data-x="'+i+'" data-y="'+j+'"></div>');    

					}
	                
					//fields[count] = field;
				}
                count++;
            }

        }
    }

    $('.fieldOpponent').click(function(){
        
       //TODO Consumir servicio de atacar
    	console.log($(this).data('x') + " " + $(this).data('y'));
        
    });


});