package com.company;

public interface Map {

    /**
     * Возвращает количество сопоставлений "ключ-значение" на этой карте.
     * Если карта содержит более элементов Integer.MAX_VALUE, возвращает Integer.MAX_VALUE.
     *
     * @return количество сопоставлений "ключ-значение" на этой карте
     */
    int size();
    /**
     * Возвращает истину, если эта карта не содержит сопоставлений "ключ-значение".
     *
     * @return истина, если эта карта не содержит сопоставлений "ключ-значение"
     */
    boolean isEmpty();
    /**
     * Возвращает истину, если эта карта содержит отображение для указанного ключа.
     * Более формально, возвращает истину тогда и только тогда, когда эта карта содержит отображение для ключа k,
     * такое что Objects.equals (key, k). (Может быть не более одного такого сопоставления.)
     *
     * @param key - ключ, наличие которого в этой карте должно быть проверено
     * @return истина, если эта карта содержит отображение для указанного ключа
     */
    boolean containsKey(String key);
    /**
     * Возвращает истину, если эта карта сопоставляет один или несколько ключей с указанным значением.
     * Более формально, возвращает true тогда и только тогда, когда эта карта содержит хотя бы одно отображение
     * на значение v, такое что Objects.equals (value, v). Эта операция, вероятно, потребует линейного времени
     * по размеру карты для большинства реализаций интерфейса Map.
     *
     * @param value, наличие которого в этой карте должно быть проверено
     * @return истина, если эта карта сопоставляет один или несколько ключей с указанным значением
     */
    boolean containsValue(Integer value);
    /**
     * Возвращает значение, которому сопоставлен указанный ключ, или null, если эта карта не содержит
     * сопоставления для ключа.
     * Более формально, если эта карта содержит отображение ключа k на значение v,
     * такое что Objects.equals (key, k), то этот метод возвращает v; в противном случае возвращается null.
     * (Может быть не более одного такого сопоставления.)
     * Если эта карта допускает значения NULL, то возвращаемое значение NULL не обязательно означает,
     * что карта не содержит сопоставления для ключа; также возможно, что карта явно отображает ключ в null.
     * Операция containsKey может использоваться для различения этих двух случаев.
     *
     * @param key - ключ, связанное значение которого должно быть возвращено
     * @return значение, которому сопоставлен указанный ключ, или null, если эта карта не содержит сопоставления для ключа
     */
    Integer get(String key);
    /**
     * Связывает указанное значение с указанным ключом на этой карте.
     * Если карта ранее содержала сопоставление для ключа, старое значение заменяется указанным значением.
     * (Считается, что карта m содержит отображение для ключа k тогда и только тогда, когда m.containsKey (k) вернет true.)
     *
     * @param key   ключ, с которым должно быть связано указанное значение
     * @param value значение, которое будет связано с указанным ключом
     * @return предыдущее значение, связанное с ключом, или null, если для ключа не было сопоставления. (Возврат null также может указывать на то, что карта ранее ассоциировала null с ключом, если реализация поддерживает нулевые значения.)
     */
    Integer put(String key, Integer value);
    /**
     * Удаляет сопоставление для ключа с этой карты, если оно присутствует. Более формально,
     * если эта Map содержит отображение из ключа k в значение v, такое, что Objects.equals (key, k),
     * это отображение удаляется. (Карта может содержать не более одного такого сопоставления.)
     * Возвращает значение, с которым эта Map ранее связала key, или null,
     * если Map не содержала сопоставления для ключа.
     * Если эта Map допускает значения NULL, то возвращаемое значение NULL не обязательно означает,
     * что Map не содержала сопоставления для ключа; также возможно,
     * что Map явно сопоставила key со значением null.
     * Карта не будет содержать сопоставление для указанного ключа после возврата вызова.
     *
     * @param key, отображение которого нужно удалить с карты
     * @return предыдущее значение, связанное с ключом, или null, если для ключа не было сопоставления.
     */
    Integer remove(String key);
    /**
     * Копирует все сопоставления с указанной карты на эту карту.
     * Эффект этого вызова эквивалентен вызову put (k, v) на этой карте один раз для каждого отображения ключа
     * k в значение v в указанной карте.
     *
     * @param mapMethod - отображения, которые будут храниться в этой маp
     */
    void putAll(Map mapMethod);
    /**
     * Удаляет все сопоставления с этой карты.
     * Карта будет пустой после того, как этот вызов вернется.
     */
    void clear();
}
