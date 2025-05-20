const cookies = getCookieMap();
const userId = cookies.userId;
const sessionId = cookies.sessionId;

function post(api, param, onSuccess, onError) {
    fetch('api', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Session-Id': document.cookie.split(';')[0],
            'X-session-User': document.cookie.split(';')[1],
        },
        body : param
    }).then(response => {
        return response.json()
    }).then(result => {
        if(onSuccess) onSuccess(result)
    }).catch(result => {
        if(onError) onError(result)
    })

}

function get (api, param, onSuccess, onError) {
    const url = api + '?' + new URLSearchParams(param).toString();
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Session-Id': sessionId,
            'X-session-User': userId,
        }
    }).then(response => {
        if (!response.ok) throw new Error("Fetch failed with status: " + response.status);
        return response.json();
    }).then(result => {
        if(onSuccess) onSuccess(result);
    }).catch(result => {
        if(onError) onError(result)
    })

}


function put(api, param, onSuccess, onError) {
    fetch(api, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Session-Id': document.cookie.split(';')[0],
            'X-session-User': document.cookie.split(';')[1],
        },
        body : param.toString()
    }).then(response => {
        return response.json()
    }).then(result => {
        if(onSuccess) onSuccess(result)
    }).catch(result => {
        if(onError) onError(result)
    })

}

function dlt(api, param, onSuccess, onError) {
    fetch(api, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Session-Id': document.cookie.split(';')[0],
            'X-session-User': document.cookie.split(';')[1],
        },
        body : param.toString()
    }).then(response => {
        if(onSuccess) onSuccess(response)
    }).catch(result => {
        if(onError) onError(result)
    })
}

function getCookieMap() {
    return document.cookie.split(';').reduce((acc, cookieStr) => {
        const [key, value] = cookieStr.trim().split('=');
        acc[key] = value;
        return acc;
    }, {});
}

window.post = post
window.put = put
window.get = get
window.dlt = dlt

