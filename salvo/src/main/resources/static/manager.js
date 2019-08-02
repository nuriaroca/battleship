$(function() {

    // display text in the output area
    function showOutput(text) {

        $("#output").text(text);

    }

// load and display JSON sent by server for /players
    function loadData() {

        //loadData() downloads the current list of players, using AJAX.
            // It does a simple GET request to the players URL.
            // When the request is answered, the code inside done() will be executed.
            // All this done() code does is display exactly the JSON received on the page.

        $.get("/players")
            .done(function(data) {

                showOutput(JSON.stringify(data, null, 2));

            })

            .fail(function( jqXHR, textStatus ) {
                showOutput( "Failed: " + textStatus );

            });

    }

// handler for when user clicks add person

    function addPlayer() {

        //addPlayer() is called when the Add button is clicked on the web page.
            // It gets data from the web form.
            // If there is a name string, it passes it to postPlayer()

        var name = $("#email").val();
        if (name) {

            postPlayer(name);

        }

    }

// code to post a new player using AJAX
// on success, reload and display the updated data from the server

    function postPlayer(userName) {

        //postPlayer() sends a JSON object with the user name to the server, using AJAX. Note several non-obvious points:
            // The URL is exactly the same as loadData(). The difference in back-end behavior is based on GET versus POST.
            // It adds a Content-Type header to the request to tell the back-end JSON is coming.
            // It uses JSON.stringify() to convert a JavaScript into a valid JSON string.
            // It sets the expected return type to text. Otherwise, jQuery will get an error trying to parse the empty content returned for the POST as JSON.
            // When the request is finished, it calls loadData() to get the new JSON.

        $.post({

            headers: {

                'Content-Type': 'application/json'

            },

            dataType: "text",
            url: "/players",
            data: JSON.stringify({ "userName": userName })

        })

            .done(function( ) {

                showOutput( "Saved – reloading");
                loadData();

            })

            .fail(function( jqXHR, textStatus ) {

                showOutput( "Failed: " + textStatus );

            });

    }
    $("#add_player").on("click", addPlayer);
    loadData();

});