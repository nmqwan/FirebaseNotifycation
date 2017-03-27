const pg = require('pg');

const config = {
    user: 'postgres', //env var: PGUSER
    database: 'H1', //env var: PGDATABASE
    password: 'admin', //env var: PGPASSWORD
    host: 'localhost', // Server hosting the postgres database
    port: 5433, //env var: PGPORT
    max: 10, // max number of clients in the pool
    idleTimeoutMillis: 1000, // how long a client is allowed to remain idle before being closed
};
const pool = new pg.Pool(config);


function queryDB(sql, cb) {
    pool.connect((err, client, done) => {
        if (err) {
            cb(`${err}`, undefined);
        } else {
            client.query(sql, (err, result) => {
                done(err); //=========gọi để end request ko gọi sẽ treo================
                if (err) {
                    cb(`${err}`, undefined);
                } else {
                    cb(undefined, result);
                }
            });
        }
    });
}

function executeQuery(sql, cb) {
    queryDB(sql, cb);
}
module.exports = { queryDB, executeQuery };