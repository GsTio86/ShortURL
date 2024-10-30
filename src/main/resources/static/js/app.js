$('#shortbutton').click(function() {
    const url = $('#longUrl').val();

    Swal.fire({
        title: '縮址中...',
        text: '請稍候',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });

    $.ajax({
        url: '/api/generate',
        type: 'POST',
        data: {
            url: url
        },
        success: function(res) {            
            const shortUrl = window.location.origin + '/' + res; // 短網址
            const qrCodeSize = 150; // QR Code 大小

            $('#longUrlLink').text(url).attr('href', url);
            $('#shortUrlLink').text(shortUrl).attr('href', shortUrl);
            $('#qrCode').attr(
                'src',
                `https://api.qrserver.com/v1/create-qr-code/?size=${qrCodeSize}x${qrCodeSize}&data=${encodeURIComponent(shortUrl)}`
            );
            $('#result').fadeIn();

            Swal.fire({
                title: '縮址成功',
                text: '短網址已產生，請查看下方結果。',
                icon: 'success'
            });
        },
        error: function(res) {
            Swal.fire({
                title: '縮址失敗',
                text: '請確認網址是否正確!',
                icon: 'error'
            });
        }
    });


});



function downloadQrCord() {
    const qrCodeUrl = $('#qrCode').attr('src');
    const link = document.createElement('a');

    const img = new Image();
    img.crossOrigin = 'anonymous'; 
    img.src = qrCodeUrl;

    img.onload = function() {
        const canvas = document.createElement('canvas');
        canvas.width = img.width;
        canvas.height = img.height;
        const ctx = canvas.getContext('2d');
        ctx.drawImage(img, 0, 0);

        const link = document.createElement('a');
        link.href = canvas.toDataURL('image/png');
        link.download = 'qrcode.png';
        link.click();
    };
}

function copyShortUrl() {
    const shortUrl = $('#shortUrlLink').attr('href');

    navigator.clipboard.writeText(shortUrl).then(() => {
        Swal.fire({
            title: '已複製短網址',
            text: shortUrl,
            icon: 'success'
        });
    }).catch(err => {
        Swal.fire({
            title: '複製失敗',
            text: '無法複製短網址',
            icon: 'error'
        });
    });
}