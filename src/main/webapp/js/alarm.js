function insertAlarmAll() {
    fetch('/webAlarm/schdle/all')
        .then(res => res.json())
        .then(result => {
            result.list.forEach(item => {
                const dateTime = new Date(item.startDt);
                const delay = dateTime.getTime() - new Date().getTime();

                if (delay > 0) {
                    console.log("알람 예약 시간:", dateTime.toLocaleString());

                    console.log(delay)
                    setTimeout(() => {
                        console.log("111")
                        new Notification("⏰ " + item.schdleTtl, {
                            body: item.schdleCn || "일정이 있습니다.",
                            // icon: "/img/logo/icon.png" // 선택적
                        });
                    }, delay);
                    console.log("222")
                }
            });
        })
        .catch(err => {
            console.error('❌ 알람 불러오기 실패:', err);
        });
}