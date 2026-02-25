declare const Badge: {
  setBadge(count: number): void;
  clearBadge(): void;
  getBadgeCount(): Promise<number>;
};

export default Badge;
