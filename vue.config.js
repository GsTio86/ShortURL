module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/ShortURL/' : '/',
    chainWebpack: config => {
        config.plugin('copy').tap(([options]) => {
            options[0].ignore.push('404.html');
            return [options];
        });
    },
    configureWebpack: {
        plugins: [
            {
                apply: (compiler) => {
                    compiler.hooks.done.tap('Copy404Plugin', () => {
                        fs.copyFileSync('dist/index.html', 'dist/404.html');
                    });
                }
            }
        ]
    }
};
