package com.christo.comp4200_a1.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "vault_table")
public class VaultEntry {
    @ColumnInfo(name = "vault_id")
    @PrimaryKey(autoGenerate = true)
    public long vaultId;

    @ColumnInfo(name = "vault_Title")
    public String webTitle;

    @ColumnInfo(name = "vault_Username")
    public String username;

    @ColumnInfo(name = "vault_PassWord")
    public String password;

    @ColumnInfo(name = "vault_Date")
    public Date entryDay;

    @ColumnInfo(name = "vault_Select")
    public Boolean isSelect;

    public VaultEntry(String webTitle, String username, String password, Date entryDay, Boolean isSelect) {
        this.webTitle = webTitle;
        this.username = username;
        this.password = password;
        this.entryDay = entryDay;
        this.isSelect = isSelect;
    }

    public long getVaultId() {
        return vaultId;
    }

    public void setVaultId(long vaultId) {
        this.vaultId = vaultId;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEntryDay() {
        return entryDay;
    }

    public void setEntryDay(Date entryDay) {
        this.entryDay = entryDay;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "{" +
                ", '" + webTitle + '\'' +
                ": '" + username + '\'' +
                ", '" + password + '}';
    }
}
