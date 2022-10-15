export function formatDate(date) {
    return new Date(date).toLocaleDateString()
}

export function initFormatters(Vue) {
    Vue.prototype.formatDate = formatDate
}
