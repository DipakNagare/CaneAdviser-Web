
var pageInitialized = true;
var values=[];
var labels=[];
var pie,svg;

if(pageInitialized){
  // alert('hello');
$.ajax({
    url : 'GetTechnologyAccesCount',
    dataType: 'json',
     success : function(response) {
         
         
//???????? ???????,4,Fertilizer Schedule,19,Fertilizers Schedule,1,Production Technologies,234,Protection Technologies,89,Saccharum Species,20,Sugarcane Varieties,89
       
        for(var key in response["list"]) {
            
            
           // alert(response["list"][key]);
           
           if(response["list"][key][0])
             labels[key]=response["list"][key][0];
             values[key]= response["list"][key][1]; 
 // alert('key'+key  +'value: ' + response[key]);
   //item[response["list"][key][0].split(" ")[0]]=response["list"][key][1];
   
   //count=count+response["list"][key][1];
   
         }
         
         
         
         
         
         
         
   
 pie = new d3pie("pieChart", {

	"footer": {
		"color": "#999999",
		"fontSize": 10,
		"font": "open sans",
		"location": "bottom-left"
	},
	"size": {
		
		"pieOuterRadius": "50%"
	},
	"data": {
		"content": [
			{
				"label": labels[0],
				"value": values[0],
				"color": "#2383c1"
			},
			{
				"label": labels[1],
				"value": values[1],
				"color": "#64a61f"
			},
			{
				"label": labels[2],
				"value": values[2],
				"color": "#7b6788"
			},
			{
				"label": labels[3],
				"value": values[3],
				"color": "#a05c56"
			},
			{
				"label": labels[4],
				"value": values[4],
				"color": "#961919"
			}
                      
		]
	},
	"labels": {
		"outer": {
			"pieDistance": 9
		},
		"mainLabel": {
			"fontSize": 11,
                        "transform":90, 
                        
		},
		"percentage": {
			"color": "#ffffff",
			"decimalPlaces": 0
		},
		"value": {
			"color": "#adadad",
			"fontSize": 11
		},
		"lines": {
			"enabled": true
		},
		"truncation": {
			"enabled": false
		}
	},
	"tooltips": {
		"enabled": true,
		"type": "placeholder",
		"string": "{label}: {value}, {percentage}%"
	},
	"effects": {
		"pullOutSegmentOnClick": {
			"effect": "linear",
			"speed": 400,
			"size": 8
		}
	},
	"misc": {
		"gradient": {
			"enabled": true,
			"percentage": 100
		}
	}
});
   
   
   
      
   
   
   

     }
});

pageInitialized=false;
}









