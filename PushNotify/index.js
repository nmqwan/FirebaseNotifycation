const express = require('express');

const app = express();

app.set('view engine', 'ejs');
app.set('views', './views');

const parser = require('body-parser').urlencoded({ extended: false });

app.listen(process.env.PORT || 3000, () => console.log('Ser started'));

const request = require('request');

const { executeQuery } = require('./DB');

function sendMessageToUser(deviceId, message, title, id) {
    request({
        url: 'https://fcm.googleapis.com/fcm/send',
        method: 'POST',
        headers: {
            'Content-Type': ' application/json',
            Authorization: 'key=AIzaSyBLhoUqRuHETSNIfj6SFNU3gTXV1Uj_l0M'
        },
        body: JSON.stringify({
            data: {
                message,
                title,
                id
            },
            to: deviceId
        })
    }, (error, response, body) => {
        if (error) {
            console.error(error, response, body);
            console.log('1');
        } else if (response.statusCode >= 400) {
            console.error(`HTTP Error: ${response.statusCode} - ${response.statusMessage}\n${body}`);
            console.log('2');
        } else {
            console.log('Done!');
        }
    });
}
// sendMessageToUser(
//     "fBvCOiSYo3U:APA91bHwZeRisnVCEddbAhUWd-nvIPtNngTZqEK976QeGCcuRay2Z2Ksr0rF7yXSN3fl_GJWFrVtgy2X0lmuHYphOcxcv-cyj-9ZPvm2LWYYjHUHUZvjIEl6_0sLaXg202hdbLZL10R5", { message: 'Hello puf' }
// );


// ================================================================================

app.get('/send', (request, response) => {
    response.render('sendMes');
});

app.post('/send', parser, (request, response) => {
    const { token, mess, name } = request.body;
    console.log(token);
    console.log(mess);
    console.log(name);
    if (mess) {
        console.log(1);
        sendMessageToUser(token, mess, 'title', 'id');
    }
    response.redirect('/send');
});

app.post('/token', parser, (request, response) => {
    const { token, name } = request.body;
    if (token) {
        const sql = `INSERT INTO public."FCM"(token, name)SELECT '${token}', '${name}' WHERE
            NOT EXISTS (
                SELECT id FROM  public."FCM" WHERE token = '${token}'
            );`;
        executeQuery(sql, e => e);
        console.log('insert token');
    }
    response.redirect('/send');
});