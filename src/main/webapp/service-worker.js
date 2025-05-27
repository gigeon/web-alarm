
self.addEventListener('push', function (event) {
    const data = event.data?.json() || { title: "새 알림", body: "내용 없음" };

    event.waitUntil(
        self.registration.showNotification(data.title, {
            body: data.body,
            icon: '/img/logo/icon.png' // 선택
        })
    );
});