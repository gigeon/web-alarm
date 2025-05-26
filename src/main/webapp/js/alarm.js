function insetAlarmAll() {
    const url = "/webAlarm" + "/schdle/all";
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(response => {
        if (!response.ok) throw new Error("Fetch failed with status: " + response.status);
        return response.json();
    }).then(result => {
        console.log(result.list)
        scheduleNotificationAt(result.list)
    }).catch(error => {
        console.error(error);
        alert("알람 등록에 실패하였습니다.");
    });
}

function scheduleNotificationAt(list) {
    const now = new Date();

    list.forEach(item => {
        // 1. 날짜 문자열을 ISO 형식으로 변환
        const dateTime = new Date(item.startDate.replace(" ", "T"));
        const delay = dateTime.getTime() - now.getTime();

        // 2. 이미 지난 일정은 생략
        if (delay <= 0) {
            console.warn(`[${item.schdleTtl}]은 과거 일정이므로 알림 생략`);
            return;
        }

        // 3. 지정된 시간에 알림 발생
        setTimeout(() => {
            new Notification(item.schdleTtl, {
                body: item.schdleCn || '일정이 있습니다.',
            });
        }, delay);
    });
}