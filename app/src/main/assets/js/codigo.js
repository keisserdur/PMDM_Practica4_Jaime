window.addEventListener("load", function(){
    var chart = new CanvasJS.Chart("chartContainer", {
        title:{
            text: "Call graphs"
        },

        data: [  //array of dataSeries
            { //dataSeries - first quarter
                /*** Change type "column" to "bar", "area", "line" or "pie"***/
                type: "column",
                name: "Incoming",
                showInLegend: true,
                dataPoints: [
                    { label: "Domingo",  y: InterfazAndroid.incoming(0)},
                    { label: "Lunes", y: InterfazAndroid.incoming(1)  },
                    { label: "Martes", y: InterfazAndroid.incoming(2)  },
                    { label: "Miercoles",  y: InterfazAndroid.incoming(3)},
                    { label: "Jueves",  y: InterfazAndroid.incoming(4)},
                    { label: "Viernes",  y: InterfazAndroid.incoming(5)},
                    { label: "Sabado",  y: InterfazAndroid.incoming(6) }
                ]
            },
            { //dataSeries - second quarter

                type: "column",
                name: "Outgoing",
                showInLegend: true,
                dataPoints: [
                    { label: "Domingo",  y: InterfazAndroid.outgoing(0)},
                    { label: "Lunes", y: InterfazAndroid.outgoing(1)  },
                    { label: "Martes", y: InterfazAndroid.outgoing(2)  },
                    { label: "Miercoles",  y: InterfazAndroid.outgoing(3)},
                    { label: "Jueves",  y: InterfazAndroid.outgoing(4)},
                    { label: "Viernes",  y: InterfazAndroid.outgoing(5)},
                    { label: "Sabado",  y: InterfazAndroid.outgoing(6) }
                ]
            }
        ]
    });
    chart.render();
});
window.addEventListener("load", function(){
    var chart = new CanvasJS.Chart("chartContainer2", {
        title:{
            text: "Total Calls"
        },
        data: [
        {
            // Change type to "doughnut", "line", "splineArea", etc.
            type: "column",
            name: "Incoming + Outgoing",
            dataPoints: [
                { label: "Domingo",  y: InterfazAndroid.total(0)},
                { label: "Lunes", y: InterfazAndroid.total(1)  },
                { label: "Martes", y: InterfazAndroid.total(2)  },
                { label: "Miercoles",  y: InterfazAndroid.total(3)},
                { label: "Jueves",  y: InterfazAndroid.total(4)},
                { label: "Viernes",  y: InterfazAndroid.total(5)},
                { label: "Sabado",  y: InterfazAndroid.total(6) }
            ]
        }
        ]
    });
    chart.render();
});