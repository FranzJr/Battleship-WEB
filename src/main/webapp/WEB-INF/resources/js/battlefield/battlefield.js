
$( document ).ready(function() {
    var fields = new Array();
    var count = 0;

    $('.container').append('<div id="battlefield"></div>');    
    var $battlefield = $('#battlefield');


    for(var i = 0; i < 10 ; i++){

        $battlefield.append('<div id="row_'+i+'" class="row"></div>');    
        var $battlefieldROW = $("#row_"+i);
        
        for(var j = 0; j < 10 ; j++){
            
            $battlefieldROW.append('<div class="field col" data-id="'+count+'" data-x="'+i+'" data-y="'+j+'"></div>');    

            var field = {
                "x": i,
                "y": j,
                "ship": false
            };
            fields[count] = field;
            count++;
        }

    }

    $('.field').click(function(){
        
        
        var countShips = 0;
        
        fields.forEach(function(fieldShip){
        	if(fieldShip.ship){
        		countShips++;
        	}
        });

        
        if(countShips < 20){
        	var field = {
                    "x": $(this).data('x'),
                    "y": $(this).data('y'),
                    "ship": true
            };
            fields[$(this).data('id')] = field;
            console.log(field);
            console.log(fields[$(this).data('id')]);
            console.log($(this).data('id') +" " + $(this).data('x') + " " + $(this).data('y'));
            
            $(this).addClass("ship");
        }else{
        	console.log("Ya ingreso todos los barcos");
        }
        
    });

    $('#createBattlefield').click(function(){

    	var countShips = 0;
        
        fields.forEach(function(fieldShip){
        	if(fieldShip.ship){
        		countShips++;
        	}
        });
        
        if(countShips == 20){
        	$.ajax({
                method: "POST",
                url: "/battlefield/create",
                contentType: "application/json",
                data: JSON.stringify(fields)
            }).done(function( msg ) {
                console.log( "Data Saved: " + msg );
                window.location.href = msg;
            }).fail(function( jqXHR, textStatus ) {
                console.log( "Request failed: " + textStatus );
              });
        }else{
        	console.log("Faltan Barcos");
        }
    	
        
    });
    

});